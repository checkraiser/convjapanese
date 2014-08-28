class CreateAndroidMetadata < ActiveRecord::Migration
  def change
    create_table :android_metadata do |t|
      t.text :locale

      t.timestamps
    end
  end
end
