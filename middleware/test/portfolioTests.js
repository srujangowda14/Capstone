const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app'); 
const expect = chai.expect;

chai.use(chaiHttp);

describe('portfolio routes', ()=>{
    it('should return portfolio when get portfolio is called', async () => {
        chai
          .request(app)
          .get('/portfolio/943842911')
          .end((err, res) => {
            console.log(res.body);
            expect(res).to.have.status(200); 
            expect(res.body.length).to.be.greaterThan(0);
          
          });
    });

    it('should return 204 when no portfolio exists', async () => {
        chai
          .request(app)
          .get('/portfolio/143842911')
          .end((err, res) => {
            expect(res).to.have.status(204); 
          
          });
    });
})

