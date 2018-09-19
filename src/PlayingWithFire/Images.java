package PlayingWithFire;

import javafx.scene.image.Image;

public class Images {
	//Menu:
	public Image blank = new Image(PlayingWithFire.class.getResource("Media/blank.jpg").toString());
	public Image gameMenu = new Image(PlayingWithFire.class.getResource("Media/gamemenu.jpg").toString());
	public Image preview1 = new Image(PlayingWithFire.class.getResource("Media/previews/preview1.jpg").toString());
	public Image preview2 = new Image(PlayingWithFire.class.getResource("Media/previews/preview2.jpg").toString());
	public Image preview3 = new Image(PlayingWithFire.class.getResource("Media/previews/preview3.jpg").toString());
	
	//Tiles:
	public Image grass = new Image(PlayingWithFire.class.getResource("Media/tiles/grass.png").toString());
	public Image rock = new Image(PlayingWithFire.class.getResource("Media/tiles/rock.png").toString());
	public Image keg = new Image(PlayingWithFire.class.getResource("Media/tiles/keg.png").toString());

	//Power-ups:
	public Image speed = new Image(PlayingWithFire.class.getResource("Media/powerups/speed.gif").toString());
	public Image biggerblasts = new Image(PlayingWithFire.class.getResource("Media/powerups/biggerblasts.gif").toString());
	public Image morebombs = new Image(PlayingWithFire.class.getResource("Media/powerups/morebombs.gif").toString());
	public Image kickbombs = new Image(PlayingWithFire.class.getResource("Media/powerups/movebombs.gif").toString());
	public Image throwbombs =  new Image(PlayingWithFire.class.getResource("Media/powerups/throw-bomb.gif").toString());
	
	//Bombs:
	public Image p1bomb = new Image(PlayingWithFire.class.getResource("Media/bombs/bomb-red.gif").toString());
	public Image p2bomb = new Image(PlayingWithFire.class.getResource("Media/bombs/bomb-blu.gif").toString());
	public Image p3bomb = new Image(PlayingWithFire.class.getResource("Media/bombs/bomb-gre.gif").toString());
	public Image p4bomb = new Image(PlayingWithFire.class.getResource("Media/bombs/bomb-pur.gif").toString());
	
	//Fronts:
	public Image p1front = new Image(PlayingWithFire.class.getResource("Media/sprites/p1front.png").toString());
	public Image p2front = new Image(PlayingWithFire.class.getResource("Media/sprites/p2front.png").toString());
	public Image p3front = new Image(PlayingWithFire.class.getResource("Media/sprites/p3front.png").toString());
	public Image p4front = new Image(PlayingWithFire.class.getResource("Media/sprites/p4front.png").toString());
	
	//Backs:
	public Image p1back = new Image(PlayingWithFire.class.getResource("Media/sprites/p1back.png").toString());
	public Image p2back = new Image(PlayingWithFire.class.getResource("Media/sprites/p2back.png").toString());
	public Image p3back = new Image(PlayingWithFire.class.getResource("Media/sprites/p3back.png").toString());
	public Image p4back = new Image(PlayingWithFire.class.getResource("Media/sprites/p4back.png").toString());
	
	//Lefts:
	public Image p1left = new Image(PlayingWithFire.class.getResource("Media/sprites/p1left.png").toString());
	public Image p2left = new Image(PlayingWithFire.class.getResource("Media/sprites/p2left.png").toString());
	public Image p3left = new Image(PlayingWithFire.class.getResource("Media/sprites/p3left.png").toString());
	public Image p4left = new Image(PlayingWithFire.class.getResource("Media/sprites/p4left.png").toString());

	//Rights:
	public Image p1right = new Image(PlayingWithFire.class.getResource("Media/sprites/p1right.png").toString());
	public Image p2right = new Image(PlayingWithFire.class.getResource("Media/sprites/p2right.png").toString());
	public Image p3right = new Image(PlayingWithFire.class.getResource("Media/sprites/p3right.png").toString());
	public Image p4right = new Image(PlayingWithFire.class.getResource("Media/sprites/p4right.png").toString());

	public Image p1bang = new Image(PlayingWithFire.class.getResource("Media/bangs/red-bang.gif").toString());
	public Image p2bang = new Image(PlayingWithFire.class.getResource("Media/bangs/blue-bang.gif").toString());
	public Image p3bang = new Image(PlayingWithFire.class.getResource("Media/bangs/green-bang.gif").toString());
	public Image p4bang = new Image(PlayingWithFire.class.getResource("Media/bangs/purple-bang.gif").toString());
	public Image offbang = new Image(PlayingWithFire.class.getResource("Media/bangs/gray-bang.gif").toString());
	public Image neutralbang = new Image(PlayingWithFire.class.getResource("Media/bangs/yellow-bang.gif").toString());



	//Arrays:
	public Image[] previews = {preview1,preview2,preview3};
	public Image[] bombs = {p1bomb,p2bomb,p3bomb,p4bomb};
	public Image[] fronts = {p1front,p2front,p3front,p4front};
	public Image[] backs = {p1back,p2back,p3back,p4back};
	public Image[] lefts = {p1left,p2left,p3left,p4left};
	public Image[] rights = {p1right,p2right,p3right,p4right};
	public Image[] bangs = {p1bang,p2bang,p3bang,p4bang,offbang,neutralbang};

}
