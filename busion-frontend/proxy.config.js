const proxy = [
	{
		context: "/mslinha",
		target: "http://localhost:8080/",
		pathRewrite: { "^/mslinha": "" },
	},
	{
		context: "/msusuario",
		target: "http://localhost:8081/api/v1",
		pathRewrite: { "^/msusuario": "" },	
	}
];
module.exports = proxy;