package dungth.testsqlite.model;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name="Lessons",  id = "id")
public class Lesson extends Model {
	@Column(name="guide")
	public String guide;
	
	@Column(name="name")
	public String name;
	public Lesson(){
		super();
	}
	public List<Context> contexts(){
		return getMany(Context.class, "lesson_id");
	}
	public static List<Lesson> getAll() {
        // This is how you execute a query
        return new Select()
          .from(Lesson.class)
          .orderBy("name")
          .execute();
    }
	public static Lesson findByName(String name){
		 return new Select().from(Lesson.class).where("name = ?", name).executeSingle();
	}
}
