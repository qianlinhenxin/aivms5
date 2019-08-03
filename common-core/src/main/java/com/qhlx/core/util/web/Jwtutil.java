package com.qhlx.core.util.web;

import com.qhlx.core.bean.JwtBean;
import com.qhlx.core.exception.ObjectNotNullException;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/28 13:25
 * @Description desc:
 */
public class Jwtutil {

    private static  Logger logger = LoggerFactory.getLogger(Jwtutil.class);


    /**
     * 密钥生成实例
     */
    private static RsaJsonWebKey rsaJsonWebKey = null;

    /**
     * 获取一个实例
     * @return
     */
    private static RsaJsonWebKey getInstance() {
        // 生成一个RSA密钥对，用于签署和验证JWT，包装在JWK中
        if (rsaJsonWebKey == null) {
            try {
                rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
                rsaJsonWebKey.setKeyId("jwt1");
            } catch (JoseException e) {
                e.printStackTrace();
            }
        }
        return rsaJsonWebKey;
    }

    /**
     * 生成一个jwt令牌
     * @param bean
     * @return
     * @throws JoseException
     * @throws ObjectNotNullException
     */
    public static JwtBean createJwt(JwtBean bean) throws JoseException, ObjectNotNullException, MalformedClaimException {
        if(bean == null){
            throw new  ObjectNotNullException("jwt object must not null:)");
        }
        // 创建claims，这将是JWT的内容 B部分
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(bean.getIssuer()); // 谁创建了令牌并签署了它
        claims.setAudience(bean.getAudience()); // 令牌将被发送给谁
        claims.setExpirationTimeMinutesInTheFuture(bean.getMinutes()); // 令牌失效的时间长（从现在开始10分钟）
        claims.setGeneratedJwtId(); // 令牌的唯一标识符
        claims.setIssuedAtToNow(); // 当令牌被发布/创建时（现在）
        claims.setNotBeforeMinutesInThePast(0); // 在此之前，令牌无效（2分钟前）
        claims.setSubject(bean.getSubject()); // 主题 ,是令牌的对象
        //添加参数
        bean.getValueMap().keySet().forEach(key->{
            claims.setClaim(key,bean.getValueMap().get(key));
        });
        //List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        //claims.setStringListClaim("groups", groups); // 多个属性/声明 也会起作用，最终会成为一个JSON数组

        // JWT是一个JWS和/或一个带有JSON声明的JWE作为有效负载。
        // 在这个例子中，它是一个JWS，所以我们创建一个JsonWebSignature对象。
        JsonWebSignature jws = new JsonWebSignature();

        // JWS的有效负载是JWT声明的JSON内容
        jws.setPayload(claims.toJson());

        // JWT使用私钥签署
        if(rsaJsonWebKey == null){
            rsaJsonWebKey = getInstance();
        }
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        /*
         * 设置关键ID（kid）头，因为这是一种礼貌的做法。 在这个例子中，我们只有一个键但是使用键ID可以帮助 促进平稳的关键滚动过程
         */
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // 在jw/jws上设置签名算法，该算法将完整性保护声明
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        /*
         * 签署JWS并生成紧凑的序列化或完整的jw/JWS 表示，它是由三个点（'.'）分隔的字符串
         * 在表单头.payload.签名中使用base64url编码的部件 如果你想对它进行加密，你可以简单地将这个jwt设置为有效负载
         * 在JsonWebEncryption对象中，并将cty（内容类型）头设置为“jwt”。
         */
        String jwt = jws.getCompactSerialization();
        bean.setJwt(jwt);
        bean.setJwtId(claims.getJwtId());
        logger.info("<------------------------生成令牌成功------------------------>");
        return bean;
    }

    /**
     * 校验jwt
     * @param bean
     * @return
     * @throws MalformedClaimException
     */
    public static JwtBean check(JwtBean bean) throws MalformedClaimException {

        String msg = "ok";
        /*
         * 使用JwtConsumer builder构建适当的JwtConsumer，它将 用于验证和处理JWT。 JWT的具体验证需求是上下文相关的， 然而,
         * 通常建议需要一个（合理的）过期时间，一个受信任的时间 发行人, 以及将你的系统定义为预期接收者的受众。
         * 如果JWT也被加密，您只需要提供一个解密密钥对构建器进行解密密钥解析器。
         */
        if(rsaJsonWebKey == null){
            rsaJsonWebKey = getInstance();
        }
        JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime() //// JWT必须有一个有效期时间
                .setAllowedClockSkewInSeconds(30) // 允许在验证基于时间的令牌时留有一定的余地，以计算时钟偏差。单位/秒
                .setRequireSubject() // 主题声明
                .setExpectedIssuer(bean.getIssuer()) // JWT需要由谁来发布,用来验证 发布人
                .setExpectedAudience(bean.getAudience()) // JWT的目的是给谁, 用来验证观众
                .setVerificationKey(rsaJsonWebKey.getKey()) // 用公钥验证签名 ,验证秘钥
                .setJwsAlgorithmConstraints( // 只允许在给定上下文中预期的签名算法,使用指定的算法验证
                        new AlgorithmConstraints(ConstraintType.WHITELIST, // 白名单
                                AlgorithmIdentifiers.RSA_USING_SHA256))
                .build(); // 创建JwtConsumer实例
        try {
            // 验证JWT并将其处理为jwtClaims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(bean.getJwt());
            //如果JWT失败的处理或验证，将会抛出InvalidJwtException。
            //希望能有一些有意义的解释（s）关于哪里出了问题。
            bean.setValueMap(jwtClaims.getClaimsMap());
            bean.setAudience(jwtClaims.getAudience().toString());
            bean.setIssuer(jwtClaims.getIssuer());
            bean.setSubject(jwtClaims.getSubject());
            bean.setJwtId(jwtClaims.getJwtId());
        } catch (InvalidJwtException e) {
            // 对JWT无效的（某些）特定原因的编程访问也是可能的
            // 在某些情况下，您是否需要不同的错误处理行为。
            // JWT是否已经过期是无效的一个常见原因
            if (e.hasExpired()) {
                msg = "当前令牌已经失效";
                logger.warn("令牌有效时间是：{}，已经失效!", e.getJwtContext().getJwtClaims().getExpirationTime());
            }
            // 或者观众是无效的
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                msg = "令牌所有者有误";
                logger.warn("令牌所有者有误：{}，不是有效的所有者!", e.getJwtContext().getJwtClaims().getExpirationTime());
            }
            logger.error("校验jwt失败,可能的原因:{}",e.getMessage());
        }
        bean.setMsg(msg);
        return bean;
    }

}
