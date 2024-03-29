## Bean校验的注解

| **Constraint**                                   | **详细信息**                                                 |
| ------------------------------------------------ | ------------------------------------------------------------ |
| **@Valid**                                       | 被注释的元素是一个对象，需要检查此对象的所有字段值           |
| **@Null**                                        | 被注释的元素必须为 null                                      |
| **@NotNull**                                     | 被注释的元素必须不为 null                                    |
| **@AssertTrue**                                  | 被注释的元素必须为 true                                      |
| **@AssertFalse**                                 | 被注释的元素必须为 false                                     |
| **@Min(value)**                                  | 被注释的元素必须是一个数字，其值必须大于等于指定的最小值     |
| **@Max(value)**                                  | 被注释的元素必须是一个数字，其值必须小于等于指定的最大值     |
| **@DecimalMin(value)**                           | 被注释的元素必须是一个数字，其值必须大于等于指定的最小值     |
| **@DecimalMax(value)**                           | 被注释的元素必须是一个数字，其值必须小于等于指定的最大值     |
| **@Size(max, min)**                              | 被注释的元素的大小必须在指定的范围内                         |
| **@Digits (integer, fraction)**                  | 被注释的元素必须是一个数字，其值必须在可接受的范围内         |
| **@Past**                                        | 被注释的元素必须是一个过去的日期                             |
| **@Future**                                      | 被注释的元素必须是一个将来的日期                             |
| **@Pattern(value)**                              | 被注释的元素必须符合指定的正则表达式                         |
| **@Email**                                       | 被注释的元素必须是电子邮箱地址                               |
| **@Length**                                      | 被注释的字符串的大小必须在指定的范围内                       |
| **@NotEmpty**                                    | 被注释的字符串的必须非空                                     |
| **@Range**                                       | 被注释的元素必须在合适的范围内                               |
| **@NotBlank**                                    | 被注释的字符串的必须非空                                     |
| **@URL(protocol=,host=, port=,regexp=, flags=)** | 被注释的字符串必须是一个有效的url                            |
| **@CreditCardNumber**                            | 被注释的字符串必须通过Luhn校验算法，银行卡，信用卡等号码一般都用Luhn计算合法性 |

## 使用方法

### 修改pom

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>
```

在User类上贴上对应注解

```java
@Data //hibernateValidator必须有set方法才能生效，此处用lombok的@Data标签代替
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 4, max = 30, message = "用户名只能在4~30位之间")
    private String username;// 用户名

    @JsonIgnore
    @Length(min = 4, max = 30, message = "用户名只能在4~30位之间")
    private String password;// 密码

    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;// 电话

    private Date created;// 创建时间

    @JsonIgnore
    private String salt;// 密码的盐值
}
```