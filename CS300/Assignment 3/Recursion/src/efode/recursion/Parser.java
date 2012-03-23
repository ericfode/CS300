package efode.recursion;



public class Parser {
	
	public boolean Parse(String str)
	{
		if(isExpression(str,0) && (takeExpression(str,0) == str.length()))
		{
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public boolean isParen(String input, int pos)
	{
		if(pos+1 <= input.length()&&input.substring(pos,pos+1).matches("(\\()")){return true;}
		else{return false;}
	}
	
	public int takeParen(String input,int pos)
	{
		if(isParen(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isCloseParen(String input, int pos)
	{
		if(pos+1 <= input.length()&&input.substring(pos,pos+1).matches("(\\))")){return true;}
		else{return false;}
	}
	
	public int takeCloseParen(String input,int pos)
	{
		if(isCloseParen(input,pos)){return pos + 1;} 
		else { return pos;}		
	}
	
	public boolean isLetter(String input, int pos)
	{
		if(pos+1 <= input.length()&&input.substring(pos,pos+1).matches("([A-Z])")){return true;}
		else{return false;}
	}
	
	public int takeLetter(String input,int pos)
	{

		if(isLetter(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isPlusMinus(String input, int pos)
	{
		if(pos+1 <= input.length()&&input.substring(pos,pos+1).matches("(\\+|\\-)")){return true;}
		else{return false;}
	}
	
	public int takePlusMinus(String input,int pos)
	{
		if(isPlusMinus(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isMultiplyDivide(String input, int pos)
	{
		if(pos+1 <= input.length()&&input.substring(pos,pos+1).matches("(\\*|\\/)")){return true;}
		else{return false;}
		
	}
	
	public int takeMultiplyDivide(String input,int pos)
	{
		if(isMultiplyDivide(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isFactor(String input, int pos)
	{

		if(isLetter(input,pos))	{return true;}
		
		if(isParen(input,pos))
		{
			pos = takeParen(input,pos);
			if(isExpression(input,pos)){
				pos = takeExpression(input,pos);
				if(isCloseParen(input,pos)){return true;}
			}
			
		}
		return false;
	}
	
	public int takeFactor(String input,int pos)
	{
		int pos1 =pos;
		if(isLetter(input,pos))	{return takeLetter(input,pos);}
		
		if(isParen(input,pos))
		{
			pos = takeParen(input,pos);
			if(isExpression(input,pos)){
				pos = takeExpression(input,pos);
				if(isCloseParen(input,pos)){return takeCloseParen(input,pos);}
			}
			
		}
		return pos1;
	}
	
	public boolean isTerm(String input, int pos)
	{
		
		if(isFactor(input,pos)){
			pos = takeFactor(input,pos);
			if(isMultiplyDivide(input,pos)){
				pos = takeMultiplyDivide(input,pos);
				if(isFactor(input,pos)){return true ;}
			}
			return true;
			}
		
		return false; 
	}
	
	public int takeTerm(String input,int pos)
	{
		int pos1 = pos;
		if(isFactor(input,pos)){
			pos = takeFactor(input,pos);
			int oldPos = pos;
			if(isMultiplyDivide(input,pos)){
				pos = takeMultiplyDivide(input,pos);
				if(isFactor(input,pos)){return takeFactor(input,pos);}
			}
			return oldPos;
			}
		
		return pos1; 
	}
	
	public boolean isExpression(String input, int pos)
	{
		
		
		if(isTerm(input,pos)){
			pos = takeTerm(input,pos);
			if(isPlusMinus(input,pos)){
				pos = takePlusMinus(input,pos);
				if(isTerm(input,pos)){
					return true ;}
			}
			return true;
			}
		
		return false;
		
	}
	
	public int takeExpression(String input,int pos)
	{
		int pos1 = pos;
		if(isTerm(input,pos)){
			pos = takeTerm(input,pos);
			int pos2 = pos;
			if(isPlusMinus(input,pos)){
				pos = takePlusMinus(input,pos);
				if(isTerm(input,pos)){
					return takeTerm(input,pos);}
			}
			return pos2;
			}
		
		return pos1;
	}
	
}
