// 글 수정을 담당하는 컨트롤러
package board.controller;
 
import javax.annotation.Resource;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
import board.dto.BoardDto;
import board.service.BoardService;
 
@Controller
public class UpdateController {
    @Resource(name="boardService")
    private BoardService boardService;
    
    @RequestMapping(value="boardUpdate.action",method=RequestMethod.GET)
    public ModelAndView updateView(@RequestParam int seq){
        ModelAndView view = new ModelAndView();
        BoardDto dto = boardService.findBySeq(seq);
        view.addObject("dto",dto);
        view.setViewName("Board_Update");
        return view;        
    }
 
    @RequestMapping(value="boardUpdate.action", method=RequestMethod.POST)
    public ModelAndView updateView(@ModelAttribute BoardDto board,@RequestParam String pass)
    {
        ModelAndView view = new ModelAndView();
        int result = boardService.updateBoard(board, pass);
        
        if(result == 1){
            view.setViewName("redirect:boardList.action");
        }else{
            view.addObject("result",result);
            view.setViewName("redirect:boardUpdate.action?seq="+board.getSeq());;
        }return view;
    }
}