const proxy = [
	{
		context: "/mslinha",
		target: "http://localhost:8080/",
		pathRewrite: { "^/mslinha": "" },
	},
	{
		context: "/msusuario",
		target: "http://localhost:8090/",
		pathRewrite: { "^/msusuario": "" },
	},
];
module.exports = proxy;
