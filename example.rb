original = []
txt = File.read('sample.txt').gsub(/\n/,' ').split(").")
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

puts original
puts meanings