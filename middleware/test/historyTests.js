const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('Trade history Routes', () => {

  it('should return trades of a valid client', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .get('/history/943842911')
        .end((err, res) => {
          expect(res).to.have.status(200); 
        });
  });

  it('should return nothing of an invalid client', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .get('/history/843842911')
        .end((err, res) => {
          expect(res.body).to.eql("");
        });
  });

});