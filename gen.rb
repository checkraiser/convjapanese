require 'fileutils'

Dir.chdir("content")
(3..131).each do |t|	
	Dir.chdir('Lesson ' + t.to_s)
	FileUtils.touch('voca.txt')
	FileUtils.touch('guide.txt')
	FileUtils.touch('example.txt')
	Dir.chdir('..')
end