<template lang="html">


    <div id="login" class="container">
        <div class="row-fluid">
            <div class="span12">
            <div class="login well well-small">
                <div class="center">
                <img src="http://placehold.it/250x100&text=Logo" alt="logo"> 
                </div>
                <form style="" class="login-form" id="UserLoginForm" >
                <div class="control-group">
                    <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span>
                    <input name="data[User][username]" required="required" placeholder="Username" maxlength="255" type="text" id="UserUsername"> 
                    </div>
                </div>
                <div class="control-group">
                    <div class="input-prepend">
                    <span class="add-on"><i class="icon-lock"></i></span>
                    <input name="data[User][password]" required="required" placeholder="Password" type="password" id="UserPassword"> 
                    </div>
                </div>
                <div class="control-group">
                    <label id="remember-me">
                    <input type="checkbox" name="data[User][remember_me]" value="1" id="UserRememberMe"> Remember Me?</label>
                </div>
                <div class="control-group">
                    <a class="btn btn-primary btn-large btn-block" type="submit" value="Sign in" @click="submitForm()">Sign in</a>
                </div>
                </form>
            </div><!--/.login-->
            </div><!--/.span12-->
        </div><!--/.row-fluid-->
     </div><!--/.container-->

</template>

<script>
import Net from '@/utils/net'

export default {
    name: 'login',
    components: {
       
    },
    methods: {
        // 重置
        resetForm() {
            
        },
        // 登录
        submitForm() {
           Net.get("http://127.0.0.1:3001/api/login/1233")
        }
    }
}
</script>
