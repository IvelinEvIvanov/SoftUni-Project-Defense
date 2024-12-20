package bg.softuni.ut.interceptor;

import org.springframework.stereotype.Service;

@Service
public class AttractionCounterInterceptorService {

	private int counter;
	private String attrTitle;

	public AttractionCounterInterceptorService() {
	}

	public void onRequest(String attrTitle) {
		this.counter++;
		this.attrTitle = attrTitle;
	}

	public AttractionCounterInterceptorView getMyInterceptorView() {
		return new AttractionCounterInterceptorView(this.counter, this.attrTitle);
	}

}
