package dungth.testsqlite.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name="Contexts",  id = "id")
public class Context extends Model {
	@Column(name="Lesson")
	public Lesson lesson;
	
	@Column(name="original")
	public String original;
	
	@Column(name="meaning")
	public String meaning;
	
	@Column(name="ctype")
	public int ctype;
	
	public Context(){
		super();
	}
}
