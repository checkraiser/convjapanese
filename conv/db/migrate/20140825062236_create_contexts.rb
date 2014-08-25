class CreateContexts < ActiveRecord::Migration
  def change
    create_table :contexts do |t|
      t.integer :lesson_id
      t.text :original
      t.text :meaning
      t.integer :ctype

      t.timestamps
    end
  end
end
