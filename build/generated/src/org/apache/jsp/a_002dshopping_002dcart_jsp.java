package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.app.domains.Product;
import com.app.domains.Order;
import java.util.Set;
import com.app.domains.OrderProduct;
import com.app.dao.OrderDao;

public final class a_002dshopping_002dcart_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/head-meta.jsp");
    _jspx_dependants.add("/main-nav.jsp");
    _jspx_dependants.add("/footer.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Shopping cart</title>\n");
      out.write("        ");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<link rel=\"shortcut icon\" href=\"images/favicon.ico\" /> \n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\" >\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap-theme.min.css\" >\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\" >\n");
      out.write("<script src=\"js/jquery.min.js\"></script>\n");
      out.write("<script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write('\n');

    String uri = request.getRequestURI();
    String pageName = uri.substring(uri.lastIndexOf("/") + 1);
    String navTheme = "navbar-default"; 
    
    /* Desc: Additional layer of security that disables the browser back button problem: 
    "Once user logges out, he is redirected to login page. By using browser's back button he 
    was able to go back to the secure page he was previously on. Refreshing the page would 
    hide the contents from the user". Also added redirects from secure pages to login page 
    Part of solution found here https://www.youtube.com/watch?v=gQLQ0t9B5yk
    */
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
    if ((pageName.equals("dashboard.jsp") || pageName.equals("cms-product-create.jsp") || 
                pageName.equals("cms-product-edit.jsp") || pageName.equals("cms-product-manage.jsp")) 
                && session.getAttribute("userid") == null) {
        response.sendRedirect("login.jsp");
        
    } else if (pageName.equals("login.jsp") && session.getAttribute("userid") != null) {
        response.sendRedirect("dashboard.jsp");
    }

      out.write('\n');
      out.write('\n');
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_if_0.setPageContext(_jspx_page_context);
      _jspx_th_c_if_0.setParent(null);
      _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userid != null && sessionScope.isadmin == true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
      if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("    ");
 navTheme = "navbar-inverse"; 
          out.write('\n');
          int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      out.write("\n");
      out.write("\n");
      out.write("<!-- Documentation http://getbootstrap.com/docs/3.3/components/#navbar -->\n");
      out.write("<!-- working example https://getbootstrap.com/docs/3.3/examples/navbar-fixed-top/-->\n");
      out.write("<!-- navbar-static-top OR navbar-fixed-top-->\n");
      out.write("<nav class=\"navbar ");
      out.print(navTheme);
      out.write(" navbar-fixed-top\" style=\"box-shadow: 0px 5px 20px #888888\"> <!-- navbar-inverse -->\n");
      out.write("    <div class=\"container\">\n");
      out.write("\n");
      out.write("        <div class=\"navbar-header\">\n");
      out.write("            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">\n");
      out.write("                <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("            </button>\n");
      out.write("            <a class=\"navbar-brand\" href=\"index.jsp\">G8-WebStore</a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("        <div id=\"navbar\" class=\"navbar-collapse collapse\">\n");
      out.write("            <ul class=\"nav navbar-nav\">\n");
      out.write("                <li class=\"");
      out.print( pageName.equals("index.jsp") || pageName.equals("") ? "active" : "");
      out.write("\"><a href=\"index.jsp\">Home<span class=\"sr-only\">(current)</span></a></li>\n");
      out.write("                <li class=\"");
      out.print( pageName.equals("about.jsp") ? "active" : "");
      out.write("\"><a href=\"about.jsp\">About</a></li>\n");
      out.write("                <li class=\"");
      out.print( pageName.equals("contact.jsp") ? "active" : "");
      out.write("\"><a href=\"contact.jsp\">Contact</a></li>\n");
      out.write("                <li class=\"");
      out.print( pageName.equals("product-list.jsp") ? "active" : "");
      out.write("\"><a href=\"product-list.jsp\">Products</a></li>\n");
      out.write("                \n");
      out.write("                <!-- Only Logged out -->\n");
      out.write("                ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_if_1.setPageContext(_jspx_page_context);
      _jspx_th_c_if_1.setParent(null);
      _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userid == null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
      if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write(" \n");
          out.write("                    <li class=\"");
          out.print( pageName.equals("login.jsp") ? "active" : "");
          out.write("\"><a href=\"login.jsp\">Login</a></li>\n");
          out.write("                ");
          int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
        return;
      }
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      out.write("\n");
      out.write("                    \n");
      out.write("                <!-- Only Logged in -->    \n");
      out.write("                ");
      if (_jspx_meth_c_if_2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                \n");
      out.write("                <!-- Only Admin -->     \n");
      out.write("                ");
      if (_jspx_meth_c_if_3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    \n");
      out.write("                <!-- Only Logged in -->   \n");
      out.write("                ");
      if (_jspx_meth_c_if_4(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    \n");
      out.write("            </ul>\n");
      out.write("        </div>    \n");
      out.write("\n");
      out.write("    </div><!-- /.container -->\n");
      out.write("</nav>");
      out.write(" \n");
      out.write("        <div class=\"container stylish-div-background\">\n");
      out.write("            <h2>Shopping cart</h2>\n");
      out.write("\n");
      out.write("            <h5>Must Be Logged In > Logic below:</h5> \n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
      out.write("   \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                            \n");
      out.write("            ");
      out.write(" \n");
      out.write("             \n");
      out.write("\n");
      out.write("                \n");
      out.write("            <div class=\"inner-div\">\n");
      out.write("\n");
      out.write("            </div> <!-- /.inner-div -->\n");
      out.write("\n");
      out.write("        </div> <!-- /.container -->\n");
      out.write("        ");
      out.write("<nav class=\"navbar navbar-default navbar-fixed-bottom\" style=\"box-shadow: 0px 0px 40px 1px #888888\"> <!-- navbar-fixed-bottom OR navbar-static-bottom-->\n");
      out.write("    <div class=\"container footer\"> \n");
      out.write("        <div class=\"row\" style=\"text-align: center;\">\n");
      out.write("            <div class=\"col-md-8\">\n");
      out.write("                <p>\n");
      out.write("                   <a href=\"about.jsp\">About us</a> |\n");
      out.write("                   <a href=\"videos.jsp\">S7-Video</a> |\n");
      out.write("                   <a href=\"terms.jsp\">Terms & Conditions</a> | \n");
      out.write("                   <a href=\"contact.jsp\">Contact us</a>\n");
      out.write("                </p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-4\">\n");
      out.write("                <p>Copyright &copy; Lovreone LLC</p>\n");
      out.write("            </div>\n");
      out.write("        </div>   \n");
      out.write("    </div> <!-- /.container --> \n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_if_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_2.setPageContext(_jspx_page_context);
    _jspx_th_c_if_2.setParent(null);
    _jspx_th_c_if_2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userid != null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_2 = _jspx_th_c_if_2.doStartTag();
    if (_jspx_eval_c_if_2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \n");
        out.write("                    <li><a href=\"dashboard.jsp\">Dashboard</a></li>\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_if_2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_2);
    return false;
  }

  private boolean _jspx_meth_c_if_3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_3.setPageContext(_jspx_page_context);
    _jspx_th_c_if_3.setParent(null);
    _jspx_th_c_if_3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userid != null && sessionScope.isadmin == true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_3 = _jspx_th_c_if_3.doStartTag();
    if (_jspx_eval_c_if_3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                    <li class=\"dropdown\">\n");
        out.write("                        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">CMS <span class=\"caret\"></span></a>\n");
        out.write("                        <ul class=\"dropdown-menu\">\n");
        out.write("                            <li><a href=\"cms-product-manage.jsp\">CMS: Manage Products</a></li>\n");
        out.write("                            <li><a href=\"cms-product-create.jsp\">CMS: Create a Product</a></li>\n");
        out.write("                            <!--li role=\"separator\" class=\"divider\"></li-->\n");
        out.write("                            <!--li><a href=\"tests/test1.jsp\" target=\"blank\">T1:JSTL Tests page</a></li-->   \n");
        out.write("                            <!--li><a href=\"http://localhost:8080/G8-JWP-c7v1/index.jsp\" target=\"blank\">Help project</a></li-->\n");
        out.write("                        </ul>\n");
        out.write("                    </li>\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_if_3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_3);
    return false;
  }

  private boolean _jspx_meth_c_if_4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_4.setPageContext(_jspx_page_context);
    _jspx_th_c_if_4.setParent(null);
    _jspx_th_c_if_4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.userid != null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_4 = _jspx_th_c_if_4.doStartTag();
    if (_jspx_eval_c_if_4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \n");
        out.write("                    <p class=\"navbar-text\">Hi, <b>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.fname}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</b></p>\n");
        out.write("                    <li>\n");
        out.write("                        <a href=\"Logout\">\n");
        out.write("                         <span class=\"glyphicon glyphicon-off\" aria-hidden=\"true\"/>   \n");
        out.write("                        </a>\n");
        out.write("                    </li>\n");
        out.write("                ");
        int evalDoAfterBody = _jspx_th_c_if_4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_4);
    return false;
  }
}
