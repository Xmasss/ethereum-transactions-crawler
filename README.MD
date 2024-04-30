### Compiles and hot-reloads for development
```
mvn clean install
```

### Build Image
```
mvn spring-boot:build-image
```

### Start container in Docker env.
```
docker run -p 8080:8080 ethereum-transactions-crawler-1.0.0:latest
```

### Ethereum transactions crawler task

Application that will allow a user to view transactions
data from the Ethereum blockchain associated with a specific 
wallet address starting with block B.


### Example: 

Transactions associated with the 
address 0xAAfCaA03fED8189b4Fd93F49E74320D18e359517 
from block 19553622 to the current block.