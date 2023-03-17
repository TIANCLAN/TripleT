var usermanage = new Vue({
	el:'#users-Vue',
	data:{
		users:[
		{
			userId:'b5dsfjnjf9-159e-4aaf-9332-fd7b294bc208',
			userName:'bb',
			userNickname:'lyy',
			userEmail:'123@qq.com',
		},
		],
	},
	async created(){
        await this.init()
        console.log("created已结束")
    },
    methods:{
    	async init(){
    		var url="http://www.triplet.com/user/AllUsers";
    		var that=this;
    		await axios.get(url).then(result =>{
    			this.userList=[]
    			let users = result.data
    			if(users.length>0){
    				console.log(users)
    				users.forEach(e => {
    					var user = e;
    					var user = {
    						userId:e.userId,
    						userName:e.userName,
    						userNickname:e.userNickname,
    						userEmail:e.userEmail,
    					}
    					that.users.push(user)
    				})
    				that.isShow = true
    			}
    		}).catch(err => {
                alert(err);
            }).finally(() => {
                console.log("现在axios刚结束")
             })
    	},
    	del(userId){
            if(userId==null){
                alert("删除参数有误");
            }
            var url="http://www.triplet.com/user/delete?userId="+userId;
            var that = this;
            axios.get(url).then(result =>{
                if(result.status==200){
                    //this.$router.replace('http://www.triplet.com/myusers.html');
                }else{
                    alert("删除有问题");
                }
                }).catch(err => {
                	alert(err);
            }).finally(() => { })
        
       }
    }
})


	
