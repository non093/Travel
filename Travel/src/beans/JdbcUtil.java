package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 이 클래스는 어떻게 하면 데이터 베이스에서 발생하는 중복코드를 간소화할지 고민
// 매번 나오는 드라이버 호출과 연결을 간소화 시킬 수 있도록 메소드 만든다
// 그리고 쉽고 편하게 부를 수 있도록 등록(= static)


public class JdbcUtil {
	//연결 생성 메소드(getConnection)
	//접근제한 반환형 이름(매개변수) - 메소드의 형태
	//연결은 커넥션. 연결을 만들어야하로 커녁션 만든다
	public static Connection getConnection(String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", username, password);
		return con;
	}
	
	
}
