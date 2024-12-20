package bg.softuni.ut.interceptor;

public class AttractionCounterInterceptorView {

	private int totalCount;
	private String title;

	public AttractionCounterInterceptorView(int totalCount, String title) {
		this.totalCount = totalCount;
		this.title=title;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
