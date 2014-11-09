package com.nityankhanna.androidutils.http;

/**
 * Created by Nityan on 2014-10-30.
 */

import static com.nityankhanna.androidutils.http.HttpStatusCode.BadGateway;

/**
 * Represents a collection of Http Status Codes.
 */
public enum HttpStatusCode
{
	/**
	 * Code 100. The client should continue with its request.
	 */
	Continue(100),

	/**
	 * Code 101. The HTTP protocol version or protocol is being changed.
	 */
	SwitchingProtocols(101),

	/**
	 * Code 200. The request succeeded and that the requested information is in the response.
	 */
	OK(200),

	/**
	 * Code 201. The request resulted in a new resource created before the response was sent.
	 */
	Created(201),

	/**
	 * Code 202. The request has been accepted for further processing.
	 */
	Accepted(202),

	/**
	 * Code 203. The returned meta-information is from a cached copy instead of the origin server and therefore may be incorrect.
	 */
	NonAuthoritativeInformation(203),

	/**
	 * Code 204. The request has been successfully processed and that the response is intentionally blank.
	 */
	NoContent(204),

	/**
	 * Code 205. The client should reset (not reload) the current resource.
	 */
	ResetContent(205),

	/**
	 * Code 206. The response is a partial response as requested by a GET request that includes a byte range.
	 */
	PartialContent(206),

	/**
	 * Code 300. The requested information has multiple representations.
	 * The default action is to treat this status as a redirect and follow the contents of the Location header associated with this response.
	 */
	MultipleChoices(300),

	/**
	 * Code 301. The requested information has been moved to the URI specified in the Location header.
	 * The default action when this status is received is to follow the Location header associated with the response.
	 */
	MovedPermanently(301),

	/**
	 * Code 302.
	 * The requested information is located at the URI specified in the Location header. The default action when this status is received is to follow the Location header associated with the response.
	 * When the original request method was POST, the redirected request will use the GET method.
	 */
	Found(302),

	/**
	 * Code 303. The response to the request can be found under another URI using a GET method. When received in response to a POST (or PUT/DELETE),
	 * it should be assumed that the server has received the data and the redirect should be issued with a separate GET message.
	 */
	SeeOther(303),

	/**
	 * Code 304.Indicates that the resource has not been modified since the version specified by the request headers If-Modified-Since or If-Match.
	 * This means that there is no need to retransmit the resource, since the client still has a previously-downloaded copy.
	 */
	NotModified(304),

	/**
	 * Code 305. The requested resource is only available through a proxy, whose address is provided in the response.
	 * Many HTTP clients (such as Mozilla and Internet Explorer)
	 * do not correctly handle responses with this status code, primarily for security reasons.
	 */
	UseProxy(305),

	/**
	 * Code 306. No longer used. Originally meant "Subsequent requests should use the specified proxy.
	 */
	Unused(306),


	/**
	 * Code 307. In this case, the request should be repeated with another URI; however, future requests should still use the original URI.
	 * In contrast to how 302 was historically implemented, the request method is not allowed to be changed when reissuing the original request.
	 * For instance, a POST request should be repeated using another POST request.
	 */
	TemporaryRedirect(307),

	/**
	 * Code 400. The server cannot or will not process the request due to something that is perceived to be a client error.
	 */
	BadRequest(400),

	/**
	 * Code 401. Similar to 403 Forbidden, but specifically for use when authentication is required and has failed or has not yet been provided.
	 * The response must include a WWW-Authenticate header field containing a challenge applicable to the requested resource.
	 * See Basic access authentication and Digest access authentication.
	 */
	Unauthorized(401),

	/**
	 * Code 402. Reserved for future use. The original intention was that this code might be used as part of some form of
	 * digital cash or micropayment scheme, but that has not happened, and this code is not usually used.
	 */
	PaymentRequired(402),

	/**
	 * Code 403. The request was a valid request, but the server is refusing to respond to it.
	 * Unlike a 401 Unauthorized response, authenticating will make no difference.
	 */
	Forbidden(403),

	/**
	 * Code 404. The requested resource could not be found but may be available again in the future.
	 * Subsequent requests by the client are permissible.
	 */
	NotFound(404),

	/**
	 * Code 405. A request was made of a resource using a request method not supported by that resource;
	 * for example, using GET on a form which requires data to be presented via POST, or using PUT on a read-only resource.
	 */
	MethodNotAllowed(405),

	/**
	 * Code 406. The requested resource is only capable of generating content not acceptable according to the Accept headers sent in the request.
	 */
	NotAcceptable(406),

	/**
	 * Code 407. The client must first authenticate itself with the proxy.
	 */
	ProxyAuthenticationRequired(407),

	/**
	 * Code 408. The server timed out waiting for the request.
	 */
	RequestTimeout(408),

	/**
	 * Code 409. Indicates that the request could not be processed because of conflict in the request,
	 * such as an edit conflict in the case of multiple updates.
	 */
	Conflict(409),

	/**
	 * Code 410. Indicates that the resource requested is no longer available and will not be available again.
	 */
	Gone(410),

	/**
	 * Code 411. The request did not specify the length of its content, which is required by the requested resource.
	 */
	LengthRequired(411),

	/**
	 * Code 412. The server does not meet one of the preconditions that the requester put on the request.
	 */
	PreconditionFailed(412),

	/**
	 * Code 413. The request is larger than the server is willing or able to process.
	 */
	RequestEntityTooLarge(413),

	/**
	 * Code 414. The URI provided was too long for the server to process.
	 * Often the result of too much data being encoded as a query-string of a GET request, in which case it should be converted to a POST request.
	 */
	RequestUriTooLong(414),

	/**
	 * Code 415. The request entity has a media type which the server or resource does not support.
	 */
	UnsupportedMediaType(415),

	/**
	 * Code 416. The client has asked for a portion of the file (byte serving), but the server cannot supply that portion.
	 */
	RequestedRangeNotSatisfiable(416),

	/**
	 * Code 417. The server cannot meet the requirements of the Expect request-header field.
	 */
	ExpectationFailed(417),

	/**
	 * Code 426. The client should switch to a different protocol such as TLS/1.0.
	 */
	UpgradeRequired(426),

	/**
	 * Code 500. A generic error message, given when an unexpected condition was encountered and no more specific message is suitable.
	 */
	InternalServerError(500),

	/**
	 * Code 501. The server either does not recognize the request method, or it lacks the ability to fulfil the request.
	 * Usually this implies future availability (e.g., a new feature of a web-service API).
	 */
	NotImplemented(501),

	/**
	 * Code 502. The server was acting as a gateway or proxy and received an invalid response from the upstream server.
	 */
	BadGateway(502),

	/**
	 * Code 503. The server is currently unavailable (because it is overloaded or down for maintenance). Generally, this is a temporary state.
	 */
	ServiceUnavailable(503),

	/**
	 * Code 504. The server was acting as a gateway or proxy and did not receive a timely response from the upstream server.
	 */
	GatewayTimeout(504),

	/**
	 * Code 505. The server does not support the HTTP protocol version used in the request.
	 */
	HttpVersionNotSupported(505);

	private int statusCode;

	private HttpStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}

	public static HttpStatusCode fromInt(int statusCode)
	{
		HttpStatusCode code = null;

		switch (statusCode)
		{
			case 100:
				code = Continue;
				break;
			case 101:
				code = SwitchingProtocols;
				break;
			case 200:
				code = OK;
				break;
			case 201:
				code = Created;
				break;
			case 202:
				code = Accepted;
				break;
			case 203:
				code = NonAuthoritativeInformation;
				break;
			case 204:
				code = Created;
				break;
			case 205:
				code = ResetContent;
				break;
			case 206:
				code = PartialContent;
				break;
			case 300:
				code = MultipleChoices;
				break;
			case 301:
				code = MovedPermanently;
				break;
			case 302:
				code = Found;
				break;
			case 303:
				code = SeeOther;
				break;
			case 304:
				code = NotModified;
				break;
			case 305:
				code = UseProxy;
				break;
			case 306:
				code = Unused;
				break;
			case 307:
				code = TemporaryRedirect;
				break;
			case 400:
				code = BadRequest;
				break;
			case 401:
				code = Unauthorized;
				break;
			case 402:
				code = PaymentRequired;
				break;
			case 403:
				code = Forbidden;
				break;
			case 404:
				code = NotFound;
				break;
			case 405:
				code = MethodNotAllowed;
				break;
			case 406:
				code = NotAcceptable;
				break;
			case 407:
				code = ProxyAuthenticationRequired;
				break;
			case 408:
				code = RequestTimeout;
				break;
			case 409:
				code = Conflict;
				break;
			case 410:
				code = Gone;
				break;
			case 411:
				code = LengthRequired;
				break;
			case 412:
				code = PreconditionFailed;
				break;
			case 413:
				code = RequestEntityTooLarge;
				break;
			case 414:
				code = RequestUriTooLong;
				break;
			case 415:
				code = UnsupportedMediaType;
				break;
			case 416:
				code = RequestedRangeNotSatisfiable;
				break;
			case 417:
				code = ExpectationFailed;
				break;
			case 426:
				code = UpgradeRequired;
				break;
			case 500:
				code = InternalServerError;
				break;
			case 501:
				code = NotImplemented;
				break;
			case 502:
				code = BadGateway;
				break;
			case 503:
				code = ServiceUnavailable;
				break;
			case 504:
				code = GatewayTimeout;
				break;
			case 505:
				code = HttpVersionNotSupported;
				break;
		}

		return code;
	}

	/**
	 * Gets the status code.
	 * @return Returns the status code.
	 */
	public int getStatusCode()
	{
		return statusCode;
	}

	/**
	 * Returns the int value of the status code as a string.
	 * @return Returns a String.
	 */
	@Override
	public String toString()
	{
		return String.valueOf(statusCode);
	}
}
