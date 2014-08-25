namespace :my_app do
  desc "TODO"
  task import: :environment do
  	content_dir = Rails.root + "../content"
  	Dir.entries(content_dir).select {|entry| File.directory? File.join(content_dir,entry) and !(entry =='.' || entry == '..') }.each do |sd|
  		puts 'processing dir ' + sd
  		lesson = Lesson.where(name: sd).first
  		unless lesson
  			lesson = Lesson.where(name: sd).create
  		end  		
  		Dir.glob(File.join(content_dir, sd) + "/*.txt").each do  |f|
  			puts 'processing ' + f
  			if f.include?('guide.txt')  				
  				lesson.guide=File.read(f).gsub(/\r\n/,' ').gsub('','  ').split('.').join("\n")				  				
				lesson.save!
  			elsif f.include?('voca.txt')
  				txt = File.read(f).gsub('','').split('.').map {|fi| fi.strip}
				original = []
				meanings = []
				txt.each do |k|
					tmp = k.split(':')
					if tmp[0].nil? or tmp[1].nil? then puts k 
					else
						original << tmp[0].strip
						meanings << tmp[1].strip
					end
				end				  				
  				lesson.contexts.where('ctype=1').delete_all
				original.each_with_index do |val, index|
					lesson.contexts.create(original: val, meaning: meanings[index], ctype: 1)
				end  				
  			elsif f.include?('example.txt')  				
  				original = []
				txt = File.read(f).gsub(/\n/,' ').split(").")
				txt1 = txt[0..-2]
				txt2 = txt.last.split(/[?.]/)
				txt1.map do |k|	
					original << k.split('.')[1].split('(')[0].strip
				end
				class Array
				  def odd_values
				    self.values_at(* self.each_index.select {|i| i.odd?})
				  end
				  def even_values
				    self.values_at(* self.each_index.select {|i| i.even?})
				  end
				end
				meanings = txt2.odd_values.map {|k| k and k.strip}
				lesson.contexts.where('ctype=2').delete_all
				original.each_with_index do |val, index|
					lesson.contexts.create(original: val, meaning: meanings[index], ctype: 2)
				end  				
  			else
  				puts 'nothing'
  			end
  		end
  	end
  		
  end

end
