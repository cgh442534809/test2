package common.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * token
 * 
 * @author 李永宁2018年11月10日下午2:37:49
 *
 */
public class JwtKit {

	/**
	 * 密钥
	 */
	private static final String TOKEN_SECRET = "XX#$%()(#*!()!KL<><MQ45LMNQN2QJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

	/**
	 * 过期时间
	 */
	// private static final long EXPIRE_TIME = 1000;

	/**
	 * 生成签名
	 * 
	 * @author 李永宁 2018年11月10日上午2:25:22
	 * 
	 * @param userId
	 *            用户id
	 * @return 加密的token
	 */
	public static String sign(JSONObject user) {
		long currentTimeMillis = System.currentTimeMillis();
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		Builder builder = JWT.create().withHeader(header).withClaim("user", user.toString()).withClaim("date",
				currentTimeMillis);
		// Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		// builder.withExpiresAt(date);
		return builder.sign(algorithm);
	}

	/**
	 * 校验token 是否正确
	 * 
	 * @author 李永宁 2018年11月10日上午2:28:17
	 * 
	 * @param token
	 *            密钥
	 * @return
	 */
	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取用户
	 * 
	 * @author 李永宁 2018年11月10日上午2:35:54
	 * 
	 * @param token
	 * @return
	 */
	public static JSONObject getUser(String token) {
		DecodedJWT jwt = JWT.decode(token);
		String str = jwt.getClaim("user").asString();
		return JSON.parseObject(str);
	}

}
