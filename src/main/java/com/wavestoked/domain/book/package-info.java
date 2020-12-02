//@org.hibernate.annotations.TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//package com.wavestoked.domain.book;
//
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;



/*
*
* Creat a package-info file in the models package and put the following contents in there:

@org.hibernate.annotations.TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
package com.ourproject.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
I used JsonBinaryType in hibernate-types-52 library.

Then in the entity class, I removed columnDefinition="jsonb" from the @Column and only used @Type(type = "jsonb")

This way, for the product, hibernate would map column to jsonb type. Then in the test folder inside the same package I added package-info with this contents:

@org.hibernate.annotations.TypeDef(name = "jsonb", typeClass = TextType.class)
package com.ourproject.model;

import org.hibernate.type.TextType;
Now when we run maven test hibernate generates varchar(2147483647) column type for the jsonb type and it solved.
*
* */


//https://stackoverflow.com/questions/39620317/how-can-solve-json-column-in-h2