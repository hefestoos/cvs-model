package com.equalsp.commons.csv;


import com.equalsp.commons.cvs.annotation.Column;
import com.equalsp.commons.cvs.annotation.Transform;
import com.equalsp.commons.cvs.annotation.Validation;
import static com.equalsp.commons.cvs.validator.Validations.*;

public class DinamicClass {
	
	@Column(order=2)
	private String string;

	@Column(order=1)
	@Transform(klass=TranformYesORNo.class)
	private String tranform;
	
	@Column(order=0)
	@Validation(types={NOTNULL})
	@Transform(klass=EnumTranformValue.class)
	private SomeEnum someEnum;
	
	@Column(order=3)
	private Object object;	
	
	private String fieldNotAnnotead;
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getTranform() {
		return tranform;
	}

	public void setTranform(String tranform) {
		this.tranform = tranform;
	}

	public SomeEnum getSomeEnum() {
		return someEnum;
	}

	public void setSomeEnum(SomeEnum someEnum) {
		this.someEnum = someEnum;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getFieldNotAnnotead() {
		return fieldNotAnnotead;
	}

	public void setFieldNotAnnotead(String fieldNotAnnotead) {
		this.fieldNotAnnotead = fieldNotAnnotead;
	}

}
