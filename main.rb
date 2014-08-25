txt = <<-SQL  
  True adjectives when used as predicates in connection with the polite verb 
gozaru change their terminations as shown in 1 92. 
The negative construction of these sentences is shown in 196. 
SQL

File.open('test.txt','w:utf-8') {|f|  f.write(txt.gsub(/\n/,' ').gsub('','  ').split('.').join("\n")) }