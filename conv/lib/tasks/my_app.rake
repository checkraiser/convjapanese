namespace :my_app do
  desc "TODO"
  task import: :environment do
  	content_dir = Rails.root + "../content"
  	Lesson.delete_all
  	Dir.entries(content_dir).select {|entry| File.directory? File.join(content_dir,entry) and !(entry =='.' || entry == '..') }.each do |sd|
  		puts 'processing dir ' + sd
  		tmp = sd.split(' ')[1].to_i
  		if  tmp < 10 then
  			sd2 = sd.split(' ')[0] + ' ' + '00' + tmp.to_s 
  			lesson = Lesson.create(name: sd2)
  		elsif tmp >= 10 and tmp < 100 then
  			sd2 = sd.split(' ')[0] + ' ' + '0' + tmp.to_s 
  			lesson = Lesson.create(name: sd2)
  		else
  			lesson = Lesson.create(name: sd)
  		end  		
  		Dir.glob(File.join(content_dir, sd) + "/*.txt").each do  |f|
  			if File.stat(f).size > 0
	  			puts 'processing ' + f
	  			if f.include?('guide.txt')  				
	  				lesson.guide=File.read(f).gsub(/\r\n/,' ').gsub('','  ').split('.').join("\n")				  				
					lesson.save!
	  			elsif f.include?('voca.txt')
	  				txt = File.read(f).gsub('','').gsub("(","[").gsub(".)","]").gsub(")","]").split('.').map {|fi| fi.strip}
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
	  				txt = File.read(f).gsub(/\n/,' ').gsub('','').gsub(/(\d+)\;/,'1.').split(/(\d+)\./)
					class Array
					  def odd_values
					    self.values_at(* self.each_index.select {|i| i.odd?})
					  end
					  def even_values
					    self.values_at(* self.each_index.select {|i| i.even?})
					  end
					end
					xtxt = txt.even_values.map {|t| t.strip }
					c = xtxt.count
					original = xtxt[1..c/2]
					meanings = xtxt[c/2+1..c-1]
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

end
