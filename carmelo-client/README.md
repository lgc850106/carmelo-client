说明
-----------------
这是carmelo项目的客户端，详细说明见carmelo-master项目下的README.md


1.框架部分
	基于netty实现服务器--客户端框架
	使用自定义的encoder和decoder实现双向对等的自定义的通信协议，双向实现Request-Response模式通信，也适用于不需要Response的单纯数据推送。
	在业务实现上,核心类为servlet类，它的init方法在启动时运行并扫描所有以Action为名称结尾的类，得到所有业务方法。在收到对端请求后，通过service方法回调对应方法。

2.业务实现
	在持久化层面上，使用了hibernate连接池，并使用了缓冲，并使用了@Transactional注解。
	在客户端认证上，使用了自定义注解功能。在服务端使用了@PassParameter和@SessionParameter注解，结合session管理机制来实现认证登录机制。在服务端使用专门的线程来进行session管理。
	在Request-Response同步机制上，使用了自定义future类，并使用Spring task和@Async来实现线程池技术，来实现在netty之外的线程池同步等待对端返回结果。另外，在心跳机制也使用spring task来运行
	在device管理上，客户端使用json文件的方式定义管理，服务端使用UserBitPort,UserComposite两个数据库表格来管理。客户端device中配置新的信息时，应将添加的设备id值设为负的int值，且所有的新id不能重复，客户端上线后将自动获取新的id并更新到配置文件中
	20181125更新状态，在client端实现了一个随机游走环境，连接服务端后，服务端随机发送游走信号，在客户端可以看到箭头随机改变方向和游走
	20181125经过重新思考，看起来理想的模型这样的，即基本的单位个体是Composite，每个连接session可以有一个或多个Composite，而Composite既可以是单例的，也可以有多个副本。每个Composite应该具备唯一的ID号，初次申请时由服务器分配。这与当前实现的session-user-composite之间一对一对一的模式不符合。所以决定重新构建整个项目。