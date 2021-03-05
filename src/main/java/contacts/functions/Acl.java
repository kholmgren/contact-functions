package contacts.functions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Acl implements Cloneable, Serializable {
    private final static Pattern aclExprPattern = Pattern.compile("(\\S+):(\\S+)#(\\S+)@(\\S+)");
    private final static Pattern usersetPattern = Pattern.compile("(\\S+):(\\S+)#(\\S+)");

    private String namespace;
    private String object;
    private String relation;
    private String user;

    private String usersetNamespace;
    private String usersetObject;
    private String usersetRelation;

    @Builder.Default
    private Long created = System.currentTimeMillis();
    @Builder.Default
    private Long updated = System.currentTimeMillis();

    public static Acl create(String aclExpression) {
        return parseAcl(aclExpression);
    }

    public boolean hasUserset() {
        return isNotEmpty(usersetNamespace) && isNotEmpty(usersetObject) && isNotEmpty(usersetRelation);
    }

    private boolean isNotEmpty(String s) {
        if (s == null) return false;
        if (s.length() == 0) return false;
        return true;
    }

    public Acl clone() {
        try {
            return (Acl) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Acl parseAcl(String aclExpr) {
        Matcher m = aclExprPattern.matcher(aclExpr);
        if (!m.find()) {
            System.out.printf("Can't parse ACL: %s%n", aclExpr);
            return null;
        }

        String namespace = m.group(1);
        String object = m.group(2);
        String relation = m.group(3);
        String user = m.group(4);

        String usersetNamespace = null;
        String usersetObject = null;
        String usersetRelation = null;

        System.out.printf("acl: %s%n", aclExpr);
        System.out.printf("acl -> namespace: %s , object: %s, relation: %s, user: %s%n", namespace, object, relation, user);

        m = usersetPattern.matcher(m.group(4));
        if (m.find()) {
            usersetNamespace = m.group(1);
            usersetObject = m.group(2);
            usersetRelation = m.group(3);

            System.out.printf("userset -> namespace: %s, object: %s, relation: %s%n", usersetNamespace, usersetObject, usersetRelation);
        }

        return Acl.builder()
            .namespace(namespace)
            .object(object)
            .relation(relation)
            .user(user)
            .usersetNamespace(usersetNamespace)
            .usersetObject(usersetObject)
            .usersetRelation(usersetRelation)
            .build();
    }
}
