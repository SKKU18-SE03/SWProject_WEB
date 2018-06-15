// 각 컨트롤러를 통해 홈페이지에서 작성받은 데이터들을 DB에 업데이트 해주는 역할
package board.mybatis;
 
 
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import board.dto.BoardDto;
 
public class BoardManager {
 
/*    창구역할을하는 sql세션팩토리빌더로 DB연결
    try부분에 sqlmapConfig은 부트스트랩
    */
    
    private static SqlSessionFactory sqlMapper;
    static{
        try {
            Reader reader = Resources.getResourceAsReader("board/mybatis/sqlmapConfig.xml");
            
            sqlMapper = new SqlSessionFactoryBuilder().build(reader); 
        } catch (IOException err){
            throw new RuntimeException("SQL세션팩토리 인스턴스 생성 실패" + err,err);
        }
    }
        
    public static List<BoardDto> boardList(String keyfield, String keyword){
        List<BoardDto> list = null;
        SqlSession session = sqlMapper.openSession();
        if(keyfield != null && keyword != null && keyfield !="" && keyword !=""){
            Map<String, String> map = new HashMap<String, String> ();
            map.put("keyfield" , keyfield);
            map.put("keyword", keyword);
            list = session.selectList("boardSearch", map);
            session.close();
            return list;
        }else {
            list = session.selectList("boardList");
            session.close();
            return list;
        }
    }
    
    public static BoardDto findBySeq(int seq){
        SqlSession session = sqlMapper.openSession();
        BoardDto board = session.selectOne("findBySeq",seq);
        session.close();
        return board;
    }
    
    public static String preView(int seq){
        SqlSession session = sqlMapper.openSession();
        String content = session.selectOne("preView",seq);
        session.close();
        return content;
    }
    
    public static void readCount(int seq){
        SqlSession session = sqlMapper.openSession();
        session.update("readCount",seq);
        session.commit();
        session.close();
    }
    
    public static void upPos(){
        SqlSession session = sqlMapper.openSession();
        session.update("upPos");
        session.commit();
        session.close();
    }
    
    public static void insertBoard(BoardDto board){
        SqlSession session = sqlMapper.openSession();
        session.insert("insertBoard",board);
        session.commit();
        session.close();
    }
    
    public static int updateBoard(BoardDto board, String pass){
        SqlSession session = sqlMapper.openSession();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seq", board.getSeq());
        map.put("content", board.getContent());
        map.put("title", board.getTitle());
        map.put("name", board.getName());
        map.put("password", pass);
        int result = session.update("updateBoard",map);
        session.commit();
        session.close();
        return result;
    }
    
    public static int deleteBoard(int seq, String storPass){
        SqlSession session = sqlMapper.openSession();
        Map map = new HashMap();
        map.put("seq", seq);
        map.put("storPass", storPass);
        int result = session.delete("deleteBoard",map);
        session.commit();
        session.close();
        return result;
    }
    
    public static String deleteView(int seq){
        SqlSession session = sqlMapper.openSession();
        String storPass = session.selectOne("deleteView",seq);
        session.close();
        return storPass;
    }
    
    public static void replyboard(BoardDto board){
        SqlSession session = sqlMapper.openSession();
        int result = session.insert("replyBoard", board);
        session.commit();
        session.close();
    }
    
    public static void replyUpPos(BoardDto board){
        SqlSession session = sqlMapper.openSession();
        session.update("replyUpPos",board);
        session.commit();
        session.close();
    }
}