package fr.uvsq.EtudiantUVSQ.Convertion;
public class Temp {
		private String item = "item";
		private String quantity = "quantity";
		private String unitPrice = "unitPrice";

		public String  getItem(){
			return this.item;
		}
		public void  setItem(String item){
			 this.item = item;
		}

		public String  getQuantity(){
			return this.quantity;
		}
		public void  setQuantity(String quantity){
			 this.quantity = quantity;
		}

		public String  getUnitPrice(){
			return this.unitPrice;
		}
		public void  setUnitPrice(String unitPrice){
			 this.unitPrice = unitPrice;
		}

// toString() Method
		 public String toString(){
			 return "{\"item\"="+item+",\"quantity\"="+quantity+",\"unitPrice\"="+unitPrice+"}";
		}
}
