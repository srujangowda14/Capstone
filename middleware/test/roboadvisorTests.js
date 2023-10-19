const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('Roboadvisor Routes', () => {

  it('should suggest instruments for buy', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .get('/roboadvisor/analyse/buy/943842911')
        .end((err, res) => {
          expect(res).to.have.status(200); 
        });
  });

  it('should suggest instrument for sell', async () => {
    const payload = {
        clientId: 943842911,
        email: 'abc@example.com'
      };
  
      chai
        .request(app)
        .get('/roboadvisor/analyse/sell/943842911')
        .end((err, res) => {
            expect(res).to.have.status(200); 
        //   expect(res.body).to.eql("");
        });
  });

});