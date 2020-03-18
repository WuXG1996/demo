package com.example.demo.domain.constant;

/**
 * @author void
 * @date 2020/3/18 17:27
 * @desc
 */
public interface CommonConstant {

    enum Sex{
        Male(1, "男"), Female(2, "女");
        private Integer code;
        private String name;

        Sex(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static Sex getSex(String name){
            for(Sex sex : values()){
                if(sex.getName().equals(name)){
                    return sex;
                }
            }
            return null;
        }
    }
}
