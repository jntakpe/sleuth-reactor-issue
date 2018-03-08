# Sleuth, Spring security issue sample 

Sample to reproduce [issue 881](https://github.com/spring-cloud/spring-cloud-sleuth/issues/881)

To reproduce the issue run the tests. Controller layer tests should pass but service layer tests should fail if sleuth is enabled.
When Sleuth is enabled ReactiveSecurityContextHolder.getContext() does not retrieve the security context.
