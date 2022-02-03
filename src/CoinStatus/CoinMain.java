package CoinStatus;

import java.io.IOException;

public class CoinMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		getCoin coin = new getCoin();
		
		coin.payCoin();
	}

}
