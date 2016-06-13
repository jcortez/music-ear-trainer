// Proxy configuration to proxy REST API calls from the front-end to the Java
// back-end in Tomcat for development.
var proxy = require('http-proxy-middleware');
var fallbackMiddleware = require('connect-history-api-fallback');

var devRESTProxy = proxy('/rest', {
    target: 'http://localhost:8080/musiceartrainer',
    changeOrigin: true
});

module.exports = {
    server: {
        middleware: {
            1: devRESTProxy,
            2: fallbackMiddleware({
                index: '/index.html'
            })
        }
    }
};
