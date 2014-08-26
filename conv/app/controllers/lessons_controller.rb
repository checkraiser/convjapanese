class LessonsController < ApplicationController
  def index
  	@lessons = Lesson.order('name')
  end

  def show
  	@lesson = Lesson.find(params[:id])  	
  	@voca = @lesson.contexts.where('ctype=1')
  	@example = @lesson.contexts.where('ctype=2')
  end
end
