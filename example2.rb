original = []
txt = File.read('sample.txt').gsub(/\n/,' ').gsub('','').gsub(/(\d+)\;/,'1.').split(/(\d+)\./)

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
puts c
puts xtxt[c/2+1..c-1]