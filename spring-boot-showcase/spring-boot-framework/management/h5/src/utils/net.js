import axios from "axios";

const http = {
  request: function(options) {
    let opts = options ? options : {};
    return new Promise((resolve, reject) => {
      axios
        .request(opts)
        .then(response => {
          resolve(response.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },
  get: function(url, params, options) {
    let opts = options ? options : {};
    return new Promise((resolve, reject) => {
      axios
        .get(url, {
          params: params
        })
        .then(response => {
          resolve(response.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },
  post: function(url, params, options) {
    let opts = options ? options : {};
    return new Promise(function(resolve, reject) {
      return new Promise((resolve, reject) => {
        axios
          .post(url, params)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    });
  }
};

export default http;
