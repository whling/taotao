# taotao
B2C Project
分布式项目部署：
  192.168.1.132：MySQL服务、taotao-manager、redis集群
  192.168.1.150：图片服务器（nginx、vsftp）、nginx反向代理服务
  192.168.1.151：solr伪分布式集群（zookeeper集群）
  192.168.1.152：taotao-portal、taotao-rest、taotao-search、taotao-sso、taotao-order
