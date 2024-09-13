package com.siemens.customerapi.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public  class Customer implements Serializable {
    @BsonId
    private long accountNo;

    private FullName fullName;

    private String email;

    private String password;

    private long contactNo;

}
