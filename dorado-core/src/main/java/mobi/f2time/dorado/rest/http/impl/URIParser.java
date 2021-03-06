/*
 * Copyright 2017 The OpenDSP Project
 *
 * The OpenDSP Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package mobi.f2time.dorado.rest.http.impl;

import mobi.f2time.dorado.Dorado;
import mobi.f2time.dorado.rest.util.StringUtils;

/**
 * 
 * @author wangwp
 */
public class URIParser {
	private String requestUri;

	private String queryString;

	private String contextPath;

	public URIParser() {
		this(Dorado.serverConfig.getContextPath());
	}

	public URIParser(String contextPath) {
		this.contextPath = contextPath;
	}

	public void parse(String uri) {
		int indx = uri.indexOf('?');

		if (indx != -1) {
			this.queryString = uri.substring(indx + 1);
			this.requestUri = uri.substring(0, indx);
		} else {
			this.requestUri = uri;
		}

		if (requestUri.startsWith(contextPath)) {
			this.requestUri = this.requestUri.substring(contextPath.length());
		}

		if (this.requestUri.endsWith("/"))
			this.requestUri = this.requestUri.substring(this.requestUri.length() - 1);

		if (StringUtils.EMPTY.equals(this.requestUri)) {
			this.requestUri = "/";
		}
	}

	public String getQueryString() {
		return queryString;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public static void main(String[] args) throws Exception {
		URIParser parser = new URIParser("/api");
		parser.parse("/api/");
		System.out.println(parser.getRequestUri());
	}
}
