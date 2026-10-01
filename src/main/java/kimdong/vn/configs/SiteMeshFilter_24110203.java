package kimdong.vn.configs;

import jakarta.servlet.annotation.WebFilter;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

@WebFilter(filterName = "sitemesh", urlPatterns = "/*")
public class SiteMeshFilter_24110203 extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// SiteMesh 3 tự hiểu thư mục gốc là /WEB-INF/decorators/
		builder.addDecoratorPath("/admin/*", "/admin_layout.jsp");
		builder.addDecoratorPath("/*", "/user_layout.jsp");

		// Loại trừ các trang không bọc layout
		builder.addExcludedPath("/login");
		builder.addExcludedPath("/register");
		builder.addExcludedPath("/verify-otp");
		builder.addExcludedPath("/static/*");
		builder.addExcludedPath("*.css");
		builder.addExcludedPath("*.js");
	}
}