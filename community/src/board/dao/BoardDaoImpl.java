package board.dao;
 
import java.util.ArrayList;
import java.util.List;
 
import board.dto.BoardDto;
import board.mybatis.BoardManager;
 
 
public class BoardDaoImpl implements BoardDao{
        
    
    // ��ӹ����� �������̵���
    @Override
    public List<BoardDto> boardList(String keyfield, String keyword) {
        List<BoardDto> result = new ArrayList<BoardDto>();
        System.out.println("���������� ���� ����");
        System.out.println(keyfield + "//" + keyword);
        result = BoardManager.boardList(keyfield, keyword);
        System.out.println(result.size());
        return result;
    }
 
    @Override
    public String preView(int seq) {
        String preContent = BoardManager.preView(seq);
        return preContent;
    }
 
    @Override
    public BoardDto findBySeq(int seq) {
        BoardDto result = BoardManager.findBySeq(seq);
        return result;
    }
 
    @Override
    public BoardDto viewContent(int seq) {
        BoardManager.readCount(seq);        // �ۺ��� ī��Ʈ�� ����
        BoardDto result = BoardManager.findBySeq(seq); // �������� ���б�
        return result;
    }
 
    // �۾����  upPos�� insertBoard �ΰ���
    public void upPos(){
        BoardManager.upPos();
        System.out.println(" ���� ���� Pos 1��");
    }
        
    public void insertBoard(BoardDto board) {
        upPos();
        BoardManager.insertBoard(board);
    }
    // --------------------------------------------- �������
 
    
    // �Խñ� ����,������ �ٷ� ���� ...... �۾���� voidó��
    public int updateBoard(BoardDto board, String pass) {
        return BoardManager.updateBoard(board, pass);
    }
 
    // �Խñ� ����,������ �ٷ� ����
    public int deleteBoard(int seq, String storPass) {
        return BoardManager.deleteBoard(seq, storPass);
    }
 
    @Override
    public String deleteView(int seq) {
        String storPass = BoardManager.deleteView(seq);
        return storPass;
    }
 
    @Override
    public void replyBoard(BoardDto board) {
        BoardManager.replyboard(board);        
    }
 
    @Override
    public void replyUpPos(BoardDto board) {
        BoardManager.replyUpPos(board);
    }
 
}