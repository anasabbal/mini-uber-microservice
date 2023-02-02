package com.nas.payment.model;

import com.nas.payment.enums.AccountStatus;
import com.nas.payment.enums.AccountType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BankAccount.class)
public abstract class BankAccount_ extends com.nas.payment.model.BaseEntity_ {

	public static volatile SingularAttribute<BankAccount, AccountType> type;
	public static volatile SingularAttribute<BankAccount, String> userId;
	public static volatile SingularAttribute<BankAccount, AccountStatus> status;

	public static final String TYPE = "type";
	public static final String USER_ID = "userId";
	public static final String STATUS = "status";

}

