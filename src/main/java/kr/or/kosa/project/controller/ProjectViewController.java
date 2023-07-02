package kr.or.kosa.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.kosa.board.service.BoardService;
import kr.or.kosa.board.vo.Board;
import kr.or.kosa.project.service.ProjectService;
import kr.or.kosa.project.vo.Project;

@Controller
public class ProjectViewController {
	
	ProjectService projectService;
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	BoardService boardService;
	
	@Autowired
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(value="/projectList.do")
	public String projectList(HttpSession session, Model model) {
		System.out.println("projectList으로 이동");
		System.out.println("Request Mapping \"/projectList.do\"");
		List<Project> list = null;
		try {
			list = projectService.selectAllProjectById((String)session.getAttribute("userid"));
			model.addAttribute("projectList", list);
		} catch (Exception e) {
			System.out.println("projectList()에서 터짐");
			System.out.println(e.getMessage());
		}
		return "project/projectList";
	}
	
	@RequestMapping(value="/projectDetail.do/{projectId}")
	public String projectList(@PathVariable("projectId") int projectId, HttpSession session) {
		System.out.println("projectId "+projectId+"으로 이동");
		System.out.println("Request Mapping \"/projectDetail.do/{projectId}\"");
		System.out.println("projectId : " + projectId);
		session.setAttribute("projectId", projectId);
		List<Board> list = null;
		try {
			list = boardService.selectBoardList(projectId);
			session.setAttribute("boardList", list);
		} catch (Exception e) {
			System.out.println("projectList()에서 터짐");
			System.out.println(e.getMessage());
		}
		
		return "project/projectDetail";
	}
}