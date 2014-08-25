class AddGuideToLesson < ActiveRecord::Migration
  def change
    add_column :lessons, :guide, :text
  end
end
