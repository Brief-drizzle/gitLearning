package sy1;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cookietime
 */
@WebServlet("/Cookietime")
public class cookietime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cookietime() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset=utf-8");
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null && cookies.length>0)
    	{
    		for(int i = 0;i<cookies.length;i++)
    		{
    			String name = cookies[i].getName();
    			if("lasttime".equals(name))
    			{
    				String value1 = cookies[i].getValue();
    				String value2 = URLDecoder.decode(value1,"utf-8");
					response.getWriter().println("��ӭ���������ϴη��ʵ�ʱ��Ϊ"+value2);
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
					String str1 = sdf.format(date);
					String str2 = URLEncoder.encode(str1, "utf-8");
					cookies[i].setValue(str2);
					cookies[i].setMaxAge(60*60*24*30);
					response.addCookie(cookies[i]);
					break;
    			}
    		}
    	}
    	if(cookies==null||cookies.length==0)
    	{
    		Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
			String str1 = sdf.format(date);
			String str2 = URLEncoder.encode(str1,"utf-8");
			Cookie cookie = new Cookie("lasttime",str2);
			cookie.setValue(str2);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			response.getWriter().println("���ã���ӭ�״ε�¼��");
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}