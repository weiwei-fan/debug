# S21-T6-BookWorm

### jdk: 11
### port: 8080
### mongodb ssl error: 
#### (src:https://bit.ly/3dAZ4z3)
1. cancel mondog auto config in springboott
2. specify ssl version
   
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Djdk.tls.client.protocols=TLSv1.2"




