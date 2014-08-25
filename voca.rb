txt = File.read('voca.txt').gsub('','').split('.').map {|f| f.strip}
original = []
meanings = []
txt.each do |k|
	tmp = k.split(':')
	if tmp[0].nil? then puts k 
	else
		original << tmp[0].strip
		meanings << tmp[1].strip
	end
end
puts original
puts meanings