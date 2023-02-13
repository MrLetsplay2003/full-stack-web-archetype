package ${package};

import org.teavm.jso.ajax.ReadyStateChangeHandler;
import org.teavm.jso.ajax.XMLHttpRequest;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;

public class Client {

	public static void main(String[] args) {
		HTMLDocument document = HTMLDocument.current();
		HTMLElement div = document.createElement("div");
		div.appendChild(document.createTextNode("TeaVM generated element"));
		document.getBody().appendChild(div);

		XMLHttpRequest xhr = XMLHttpRequest.create();
		xhr.open("GET", "/hello");
		xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {

			@Override
			public void stateChanged() {
				if(xhr.getReadyState() == XMLHttpRequest.DONE) {
					document.getBody().appendChild(document.createTextNode(xhr.getResponseText()));
				}
			}
		});
		xhr.send();
	}

}
