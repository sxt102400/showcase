const path = require("path");
const  chalk = require("chalk");

function resolve(dir) {
  return path.join(__dirname, dir);
}

module.exports = {
  configureWebpack: config => {},
  chainWebpack: config => {
    config.plugin("env").use(require.resolve("webpack/lib/ProvidePlugin"), [
      {
        $: "jquery",
        jQuery: "jquery"
      }
    ]);

    config.plugin("html").tap(args => {
      args[0].minify = false;
      return args;
    });

    config.resolve.alias
      .set("@", resolve("src"))
      .set("static", resolve("../static"));
    config.toString();
  },
  devServer: {
    open: process.platform === "darwin",
    host: "0.0.0.0",
    port: process.env.VUE_APP_PORT,
    https: false,
    hotOnly: false,
    proxy: {
      "/api": {
        target: process.env.VUE_APP_PROXY_URL,
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        },
        onProxyReq: (proxyReq, req, res) => {},
        onProxyRes: (proxyRes, req, res) => {
          console.log(`[response] =>  ${req.method} ${proxy.target}${req.url}  =>  `+ chalk.green(`${resp.statusCode}`));
        }
      }
    }, // 设置代理
    before: app => {}
  }
};
