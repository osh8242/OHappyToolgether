package kr.or.kosa.project.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosa.project.dao.ProjectDao;
import kr.or.kosa.project.vo.Project;
import kr.or.kosa.project.vo.UsersProject;

@Service
public class ProjectService {
	
	SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public List<Project> selectAllProjectById(String userid){
		System.out.println("selectAllProjectById() 시작");
		List<Project> list = null;
		ProjectDao dao = sqlsession.getMapper(ProjectDao.class);
		System.out.println("ProjectDao 맵퍼 가져옴");
		try {
			list = dao.selectAllProjectById(userid);
			System.out.println("list 사이즈 : " + list.size());
			System.out.println("projectList 가져옴");
		} catch (Exception e) {
			System.out.println("dao.selectAllProjectById(userid) 에서 터짐");
			e.getStackTrace();
			System.out.println(e.getMessage());
		}		
		System.out.println("selectAllProjectById 리턴");
		return list;
	}
	
	public int insertProject(Project project) {
		System.out.println("insertProject 서비스 시작");
		int result = 0;
		ProjectDao dao = sqlsession.getMapper(ProjectDao.class);
		System.out.println("ProjectDao 맵퍼 가져옴");
		try {
			result = dao.insertProject(project);
			System.out.println("insertProject 처리함");
		} catch (Exception e) {
			e.getStackTrace();
		}		
		return result;
	}
	
	public int deleteProject(String projectId) {
		int result = 0;
		ProjectDao dao = sqlsession.getMapper(ProjectDao.class);
		System.out.println("ProjectDao 맵퍼 가져옴");
		try {
			result = dao.deleteProject(projectId);
			System.out.println("deleteProject 처리함");
		} catch (Exception e) {
			e.getStackTrace();
		}		
		return result;
	}
	
	public int insertUsersProject(UsersProject usersProject) {
		int result = 0;
		ProjectDao dao = sqlsession.getMapper(ProjectDao.class);
		System.out.println("ProjectDao 맵퍼 가져옴");		
		System.out.println("userid : " + usersProject.getUserid());
		System.out.println("projectId : " + usersProject.getProjectId());
		try {			
			result = dao.insertUsersProject(usersProject);
			System.out.println("insertUsersProject 처리함");
		} catch (Exception e) {
			e.getStackTrace();
		}		
		return result;
	}
	
	public int deleteUsersProject(UsersProject usersProject) {
		int result = 0;
		ProjectDao dao = sqlsession.getMapper(ProjectDao.class);
		System.out.println("ProjectDao 맵퍼 가져옴");			
		try {			
			result = dao.deleteUsersProject(usersProject);
			System.out.println("deleteUsersProject 처리함");
		} catch (Exception e) {
			e.getStackTrace();
		}		
		return result;
	}
}
