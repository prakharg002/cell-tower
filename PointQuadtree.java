public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean insert(CellTower a) {
        // TO be completed by students
       // System.out.println("insert:"+a.x+" "+a.y+ " "+a.cost );
        PointQuadtreeNode temp = this.root;
        PointQuadtreeNode newNode = new PointQuadtreeNode(a) ;
        if(this.root == null)
        {
          this.root = newNode;
          return true;
          
        }
      else
      {  while (temp != null )
            {    
                if(newNode.celltower.x < temp.celltower.x && newNode.celltower.y >= temp.celltower.y)
                            {
                                if(temp.quadrants[Quad.NW.ordinal()] == null)
                                    {
                                        temp.quadrants[Quad.NW.ordinal()] = newNode;

                                        return true;                                
                                    }
                                
                                temp = temp.quadrants[Quad.NW.ordinal()];
                                
                            }
                if(newNode.celltower.x >= temp.celltower.x && newNode.celltower.y > temp.celltower.y)
                            {
                                if(temp.quadrants[Quad.NE.ordinal()] == null)
                                    {
                                        temp.quadrants[Quad.NE.ordinal()] = newNode;
                                        return true;                                
                                    }

                                temp = temp.quadrants[Quad.NE.ordinal()];
                                
                            }
                if(newNode.celltower.x <= temp.celltower.x && newNode.celltower.y < temp.celltower.y)
                            {
                                if(temp.quadrants[Quad.SW.ordinal()] == null)
                                    {
                                        temp.quadrants[Quad.SW.ordinal()] = newNode;
                                        return true;                                
                                    }

                                temp = temp.quadrants[Quad.SW.ordinal()];
                                
                            }              
                if(newNode.celltower.x > temp.celltower.x && newNode.celltower.y <= temp.celltower.y)
                            {
                                if(temp.quadrants[Quad.SE.ordinal()] == null)
                                    {
                                        temp.quadrants[Quad.SE.ordinal()] = newNode;
                                        return true;                                
                                    }           

                                temp = temp.quadrants[Quad.SE.ordinal()];
                                
                            }  
                if(newNode.celltower.x == temp.celltower.x && newNode.celltower.y == temp.celltower.y)
                            {
                                return false;
                            }
            }                 
                       
        }  
            
        return false;
    }

    public boolean cellTowerAt(int x, int y) {
        // TO be completed by students
    PointQuadtreeNode temp = this.root;    
    CellTower ct = new CellTower(x, y, 0);
    PointQuadtreeNode newNode = new PointQuadtreeNode(ct);

    while(temp != null)
        {

    
            if(newNode.celltower.x < temp.celltower.x && newNode.celltower.y >= temp.celltower.y)
                    {
                        if(temp.quadrants[Quad.NW.ordinal()] == null)
                            {
                                return false;                                
                            }
                        
                    temp = temp.quadrants[Quad.NW.ordinal()] ;
                        
                    }
            if(newNode.celltower.x >= temp.celltower.x && newNode.celltower.y > temp.celltower.y)
                    {
                        if(temp.quadrants[Quad.NE.ordinal()] == null)
                            {
                                return false;                                
                            }

                        temp = temp.quadrants[Quad.NE.ordinal()];
                        
                    }
            if(newNode.celltower.x <= temp.celltower.x && newNode.celltower.y < temp.celltower.y)
                    {
                        if(temp.quadrants[Quad.SW.ordinal()] == null)
                            {
                                return false;                                
                            }

                        temp = temp.quadrants[Quad.SW.ordinal()];
                        
                    }              
            if(newNode.celltower.x > temp.celltower.x && newNode.celltower.y <= temp.celltower.y)
                    {
                        if(temp.quadrants[Quad.SE.ordinal()] == null)
                            {
                                return false;                                
                            }                                
                        temp = temp.quadrants[Quad.SE.ordinal()];
                        
                    }  
            if(newNode.celltower.x == temp.celltower.x && newNode.celltower.y == temp.celltower.y)
                    {
                        return true;
                    } 


        }            
        return false;
    }
    private CellTower mincost(PointQuadtreeNode  t , int x , int y , int r )
    {   CellTower r_t = null ;
       CellTower n_w = null ;
       CellTower n_e = null ;
       CellTower s_w= null ;
       CellTower s_e = null ;
       CellTower currmin = null;
        CellTower ct = t.celltower;

       if(ct.distance(x, y) <= r)
        {   
            if (r_t == null)
                {  
                    r_t = ct;
                    
                }
        

        }

       if (t.quadrants[Quad.SW.ordinal()] == null && t.quadrants[Quad.SE.ordinal()] == null && t.quadrants[Quad.NW.ordinal()]== null && t.quadrants[Quad.NE.ordinal()] == null)
       { 
         
            if(ct.distance(x, y) <= r)
                    { 
                        return ct;
                    }
            else 
            {
                return null;
            }
       }

    //case1:intersection with one quadrant only
            if(ct.x > x+r && ct.y >= y+r)
                { //go to SW 
                    if(t.quadrants[Quad.SW.ordinal()]!= null)
                    {s_w = mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}

                    if(ct.y==y+r)
                    {    
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                    {   
                        n_w = mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);
                       
                    }

                    }
    
                }
            
            if(ct.x >= x+r && ct.y < y-r)
                { //go to NW
                    
                    if(t.quadrants[Quad.NW.ordinal()] != null)
                    {   
                        n_w = mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);
                       
                    }
                    if(ct.x==x+r)
                    {    
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                            {   
                                n_e = mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);
                            
                            }

                    }

    
                }
            if(ct.x <= x-r && ct.y > y+r)
                {//go to SE
                    if(t.quadrants[Quad.SE.ordinal()] != null)
                    {s_e = mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                    if(ct.x==x-r)
                    {    
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                    {   
                        s_w = mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);
                       
                    }

                    }
    
                }
            if(ct.x < x-r && ct.y <= y-r)
                {//go to NE
                    if(t.quadrants[Quad.NE.ordinal()] != null)
                    {n_e = mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}

                    if(ct.y==y-r)
                    {    
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                    {   
                        s_e = mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);
                       
                    }

                    }
    
                } 

    //case2:intersection with two quadrants    
        if(ct.x<=x-r && y-r<ct.y && ct.y<y+r)
           {
              if (ct.x == x-r && y<ct.y)
                    {//go to NE,SE,SW....here we check in three quadrants
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                       {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                       if(t.quadrants[Quad.SE.ordinal()] != null)
                       {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                       if(t.quadrants[Quad.SW.ordinal()] != null)
                       {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                          
                    }

              else
                {//go to NE,SE
                    if(t.quadrants[Quad.NE.ordinal()] != null)
                    {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                    if(t.quadrants[Quad.SE.ordinal()] != null)
                    {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}

                    
                }  
           }
        if(ct.x>=x+r && y-r<ct.y && ct.y<y+r)
           {
                if( ct.x == x+r && y>ct.y )
                    {//go to NW,SW,NE....here we check in three quadrants
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                        {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                             
                    }
                else
                    {//go to NW, SW
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                         {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                         if(t.quadrants[Quad.SW.ordinal()] != null)
                         {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
  
                    }    

           }
       
        if(ct.y<=y-r && x-r<ct.x && ct.x<x+r)
           { 
              if (ct.y == y-r && x<ct.x)
                    {//go to NE,NW,SE....here we check in three quadrants
                      
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                        {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}

                    }
              else
                {//go to NE,NW
                    
                    if(t.quadrants[Quad.NE.ordinal()] != null)
                     {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);
                     
                    }
                   
                    if(t.quadrants[Quad.NW.ordinal()] != null)
                     {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);
                    
                    }
                }  
           } 
        if(ct.y>=y+r && x-r<ct.x && ct.x<x+r)
           {
                if( ct.y == y+r && x>ct.x )
                    {//go to SE,SW,NW....here we check in three quadrants
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}

                    }
                else
                    {//go to SE, SW
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                        
                    }    

           }  

    //case3:search in three quadrant
        if(ct.distance(x, y) < Math.sqrt(2)*r && ct.distance(x, y) >=r)
            {
                if(x>ct.x && y > ct.y)
                    {//go to NW,NE,SE
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                        {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                      
                    }
                if(x<ct.x && y > ct.y)
                    {//go to NW,NE,SW
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                        {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                      
                    } 
                if(x>ct.x && y < ct.y)
                    {//go to NE,SE,SW
                        if(t.quadrants[Quad.NE.ordinal()] != null)
                        {n_e =  mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                      
                    }
                if(x<ct.x && y < ct.y)
                    {//go to NW,SW,SE
                        if(t.quadrants[Quad.SE.ordinal()] != null)
                        {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.SW.ordinal()] != null)
                        {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);}
                        if(t.quadrants[Quad.NW.ordinal()] != null)
                        {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);}
                   
                    }                                                           
            
        }  
    //case4:search in all 4 quadrant        
        if(ct.distance(x, y) < r)
            { //search in all 4
                if(t.quadrants[Quad.SE.ordinal()] != null)
                {s_e =  mincost(t.quadrants[Quad.SE.ordinal()], x, y, r);
                   
                }
               
                if(t.quadrants[Quad.SW.ordinal()] != null)
                {s_w =  mincost(t.quadrants[Quad.SW.ordinal()], x, y, r);
                    
                }
               
                if(t.quadrants[Quad.NW.ordinal()] != null)
                {n_w =  mincost(t.quadrants[Quad.NW.ordinal()], x, y, r);
                   
                }
               
                if(t.quadrants[Quad.NE.ordinal()] != null)
                {n_e = mincost(t.quadrants[Quad.NE.ordinal()], x, y, r);
                   
                }
                
            }  

                  
        
            if(r_t!=null)
                {
                    currmin=r_t;
                }
            if (s_w!=null)
                {
                    if (currmin==null)
                        {
                            currmin=s_w;
                        }
                    else
                        {
                            if (s_w.cost<currmin.cost)
                                {
                                    currmin=s_w;
                                }
                        }
                }
            if (s_e!=null)
            {
                if (currmin==null)
                    {
                        currmin=s_e;
                    }
                else
                {
                    if (s_e.cost<currmin.cost)
                        {
                            currmin=s_e;
                        }
                }
            }
            if (n_w!=null)
            {
                if (currmin==null)
                    {
                        currmin=n_w;
                    }
                else
                    {
                        if (n_w.cost<currmin.cost)
                        {
                            currmin=n_w;
                        }
                    }
            }
            if (n_e!=null)
            {
                if (currmin==null)
                    {
                        currmin=n_e;
                    }
                else
                    {
                        if (n_e.cost<currmin.cost)
                        {
                            currmin=n_e;
                        }
                    }
            }
         
      return currmin;
    }

    public CellTower chooseCellTower(int x, int y, int r) {
        // TO be completed by students
       
        PointQuadtreeNode t0 = this.root;
        
        CellTower a = mincost(t0,x,y,r) ;   
     
    
        return a;
    }
    












}
