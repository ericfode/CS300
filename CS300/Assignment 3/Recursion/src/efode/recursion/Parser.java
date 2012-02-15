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
		if(input.substring(pos,pos+1).matches("(\\()")){return true;}
		else{return false;}
	}
	
	public int takeParen(String input,int pos)
	{
		if(isParen(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isCloseParen(String input, int pos)
	{
		if(input.substring(pos,pos+1).matches("(\\))")){return true;}
		else{return false;}
	}
	
	public int takeCloseParen(String input,int pos)
	{
		if(isCloseParen(input,pos)){return pos + 1;} 
		else { return pos;}		
	}
	
	public boolean isLetter(String input, int pos)
	{
		if(input.substring(pos,pos+1).matches("([A-Z])")){return true;}
		else{return false;}
	}
	
	public int takeLetter(String input,int pos)
	{

		if(isLetter(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isPlusMinus(String input, int pos)
	{
		if(input.substring(pos,pos+1).matches("(\\+|\\-)")){return true;}
		else{return false;}
	}
	
	public int takePlusMinus(String input,int pos)
	{
		if(isPlusMinus(input,pos)){return pos + 1;} 
		else { return pos;}
	}
	
	public boolean isMultiplyDivide(String input, int pos)
	{
		if(input.substring(pos,pos+1).matches("(\\*|\\/)")){return true;}
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
			if(pos == input.length()||!isExpression(input,pos)){return false;}
			pos = takeExpression(input,pos);
			if(pos == input.length()||!isCloseParen(input,pos)){return false;}
			
		}
		return true;
	}
	
	public int takeFactor(String input,int pos)
	{
		if(isLetter(input,pos))
		{
			return takeLetter(input,pos);
		}
		
		if(isParen(input,pos))
		{
			pos = takeParen(input,pos);
			pos = takeExpression(input, pos);
			if(pos != input.length()||isCloseParen(input,pos))
			{
				pos=takeCloseParen(input,pos);
				return pos;
			}
		}
		
		//else
		return pos;
	}
	
	public boolean isTerm(String input, int pos)
	{
		
		boolean case1 = false;
		case1 = isFactor(input,pos);
		
		if(pos == input.length()||!isFactor(input,pos)){return false;}
		pos = takeFactor(input,pos);
		if(pos == input.length()||!isMultiplyDivide(input,pos)){return false ||case1;}
		pos = takeMultiplyDivide(input,pos);
		if(pos == input.length()||!isFactor(input,pos)){return false || case1;}
		pos = takeFactor(input,pos);
		return true; 
	}
	
	public int takeTerm(String input,int pos)
	{
	 	pos = takeFactor(input,pos);
	 	if(pos == input.length()||!isMultiplyDivide(input, pos)){ return pos;}
	 	
	 	pos = takeMultiplyDivide(input, pos);
	 	pos = takeTerm(input, pos);
	 	
	 	return pos;
	}
	
	public boolean isExpression(String input, int pos)
	{
		
		boolean case1 = false;
		case1 = isTerm(input,pos);
		
		if(!isTerm(input,pos)){return false || case1;}
		pos = takeTerm(input,pos);
		if(pos == input.length()||!isPlusMinus(input,pos)){return false ||case1;}
		pos = takePlusMinus(input,pos);
		if(pos == input.length()||!isTerm(input,pos)){return false || case1;}
		pos = takeTerm(input,pos);
		return true;
		
	}
	
	public int takeExpression(String input,int pos)
	{
		//case 1
		pos = takeTerm(input, pos);
		if(pos == input.length()||!isPlusMinus(input, pos)){return pos;}
		
		pos = takePlusMinus(input, pos);
		pos = takeTerm(input, pos);
		
		return pos;
	}
	
}
